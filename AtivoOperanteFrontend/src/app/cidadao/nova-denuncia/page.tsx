'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/contexts/AuthContext';

interface ProblemType {
  id: string;
  name: string;
}

interface Organization {
  id: string;
  name: string;
}

export default function NewComplaint() {
  const router = useRouter();
  const { user } = useAuth();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [selectedOrg, setSelectedOrg] = useState('');
  const [selectedProblemType, setSelectedProblemType] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  // TODO: Replace with actual data from API
  const organizations: Organization[] = [];
  const problemTypes: ProblemType[] = [];

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      const response = await fetch('/api/denuncia', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          title,
          description,
          organizationId: selectedOrg,
          problemTypeId: selectedProblemType,
          userId: user?.id,
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to submit complaint');
      }

      console.log({
        title,
        description,
        organizationId: selectedOrg,
        problemTypeId: selectedProblemType,
        userId: user?.id,
      });

      router.push('/cidadao');
    } catch (error) {
      console.error('Error submitting complaint:', error);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="space-y-6">
      <div className="md:grid md:grid-cols-3 md:gap-6">
        <div className="md:col-span-1">
          <h3 className="text-lg font-medium leading-6 text-gray-900">
            Nova Denúncia
          </h3>
          <p className="mt-1 text-sm text-gray-500">
            Preencha os dados da sua denúncia abaixo.
          </p>
        </div>
        <div className="mt-5 md:mt-0 md:col-span-2">
          <form onSubmit={handleSubmit}>
            <div className="shadow sm:rounded-md sm:overflow-hidden">
              <div className="px-4 py-5 bg-white space-y-6 sm:p-6">
                <div>
                  <label
                    htmlFor="title"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Título
                  </label>
                  <input
                    type="text"
                    name="title"
                    id="title"
                    required
                    className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                  />
                </div>

                <div>
                  <label
                    htmlFor="description"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Descrição
                  </label>
                  <textarea
                    id="description"
                    name="description"
                    rows={3}
                    required
                    className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>

                <div>
                  <label
                    htmlFor="organization"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Órgão Responsável
                  </label>
                  <select
                    id="organization"
                    name="organization"
                    required
                    className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    value={selectedOrg}
                    onChange={(e) => setSelectedOrg(e.target.value)}
                  >
                    <option value="">Selecione um órgão</option>
                    {organizations.map((org) => (
                      <option key={org.id} value={org.id}>
                        {org.name}
                      </option>
                    ))}
                  </select>
                </div>

                <div>
                  <label
                    htmlFor="problemType"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Tipo do Problema
                  </label>
                  <select
                    id="problemType"
                    name="problemType"
                    required
                    className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    value={selectedProblemType}
                    onChange={(e) => setSelectedProblemType(e.target.value)}
                  >
                    <option value="">Selecione um tipo</option>
                    {problemTypes.map((type) => (
                      <option key={type.id} value={type.id}>
                        {type.name}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
              <div className="px-4 py-3 bg-gray-50 text-right sm:px-6">
                <button
                  type="submit"
                  disabled={isSubmitting}
                  className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  {isSubmitting ? 'Enviando...' : 'Enviar Denúncia'}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
} 